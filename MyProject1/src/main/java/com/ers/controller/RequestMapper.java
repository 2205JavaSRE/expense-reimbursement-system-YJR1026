package com.ers.controller;

import java.io.File;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class RequestMapper {
	
	private ReimbursementController rController = new ReimbursementController();	
	
	public void configureRoutes(Javalin app) {
		
		PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
		registry.config().commonTags("application","ERS-Monitoring");
		
		new ClassLoaderMetrics().bindTo(registry);
		new JvmMemoryMetrics().bindTo(registry);
		new JvmGcMetrics().bindTo(registry);
		new JvmThreadMetrics().bindTo(registry);
		new UptimeMetrics().bindTo(registry);
		new ProcessorMetrics().bindTo(registry);
		new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);
		
		Counter counter = Counter.builder("Path_request").description("track number").tag("purpose", "request_submit").register(registry);
		Counter counter1 = Counter.builder("Path_request").description("track number").tag("purpose", "reimbursment").register(registry);
		Counter counter2 = Counter.builder("Path_request").description("track number").tag("purpose", "login").register(registry);
		Counter counter3 = Counter.builder("Path_request").description("track number").tag("purpose", "reimbursement_check").register(registry);
		
		app.get("/metrics", ctx->{
			ctx.result(registry.scrape());
			
		});
			
		app.post("/api/requestSubmit", ctx ->{

			boolean access = AuthenticationController.sessionCheck(ctx);
	
			if(access) {
				rController.requestSubmit(ctx);	
				counter.increment(1);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}	
			
		} );
		
		app.get("/api/reimbursements",ctx->{			
			
			boolean access = AuthenticationController.managerCheck(ctx);
			
			if(access) {
				rController.allReimbursment(ctx);
				counter1.increment(1);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}	
		});
		
		app.post("/login", ctx->{
			
			AuthenticationController.authenticate(ctx);
			counter2.increment(1);

		});
		app.get("/logout", ctx->{
			
			AuthenticationController.logout(ctx);
			
		});
		app.get("/session/secret", ctx ->{
			
			AuthenticationController.sessionCheck(ctx);
		});
			
		app.get("/reimbursement/{username}/{status}", ctx->{
			
						
			boolean access = AuthenticationController.sessionCheck(ctx);
			
			if(access) {
				rController.statusCheck(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
						
		});
		
		app.get("/reimbursement/{username}", ctx->{
			
			boolean access = AuthenticationController.sessionCheck(ctx);
			
			if(access) {
				rController.usernameCheck(ctx);
				counter3.increment(1);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}		
			
		});
				
		app.post("/mlogin", ctx -> {
			
			AuthenticationController.authenticateManager(ctx);

		});
		
		app.get("/mlogout", ctx -> {
			
			AuthenticationController.logoutManager(ctx);

		});		
		
		app.get("/manager/{username}/{status}", ctx->{
								
			boolean access = AuthenticationController.managerCheck(ctx);
			
			if(access) {
				rController.managerStatusCheck(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
							
		});
		
		app.get("/manager/{uniqname}", ctx->{
			
			boolean access = AuthenticationController.managerCheck(ctx);
			if(access) {
				rController.managerCheck(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
						
		});
		
		
		app.put("/api/paymentupdate", ctx ->{

			boolean access = AuthenticationController.managerCheck(ctx);
	
			if(access) {
				rController.paymentStatusUpdate(ctx);			
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
								
		} );
				
		app.get("/api/reimbursements/all",ctx->{			
			
			boolean access = AuthenticationController.managerCheck(ctx);
			
			if(access) {
				rController.allReimbursement(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}	
		});
		
		app.get("/managerall/{uniqname}", ctx->{
			
			boolean access = AuthenticationController.managerCheck(ctx);
			
			if(access) {
				rController.allReimbursementByUsername(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}			
			
		});
				
	}
	
}
