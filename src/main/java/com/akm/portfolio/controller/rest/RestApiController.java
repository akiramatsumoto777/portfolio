package com.akm.portfolio.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.akm.portfolio.controller.dto.rest.ChartData;
import com.akm.portfolio.controller.dto.rest.CurrentDropData;
import com.akm.portfolio.domain.service.packet.PacketCheckService;

@RestController
public class RestApiController {

	@Autowired
	PacketCheckService packetCheckService;

	@GetMapping("chartData/{channelId}")
	ChartData getChartData(@PathVariable("channelId") Integer channelId) {		
		return packetCheckService.getChartData(channelId);
	}

	@GetMapping("currentDropData")
	CurrentDropData getChartData() {		
		return packetCheckService.getCurrentDropData();
	}

}
