package com.akm.portfolio.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.akm.portfolio.constants.MachineStatus;
import com.akm.portfolio.controller.dto.web.PacketCheckDetailDisp;
import com.akm.portfolio.controller.dto.web.PacketCheckRateDisp;
import com.akm.portfolio.domain.model.packet.Channel;
import com.akm.portfolio.domain.model.packet.DropLog;
import com.akm.portfolio.domain.service.packet.PacketCheckService;


@Controller
@RequestMapping("packetCheck")
public class PacketCheckController {

	@Autowired
	PacketCheckService packetCheckService;

	@RequestMapping(method = RequestMethod.GET)
	String packetCheck(Model model) {
				
		// ドロップ率表示用のデータを取得
		List<DropLog> dropRatesInfo = getDropRates();
		
		// 表示用Dtoを生成
		List<PacketCheckDetailDisp> channelsInfo = getDetailInfo();
		
		// modelに情報設定
		model.addAttribute("dropRates", dropRatesInfo);
		model.addAttribute("channels", channelsInfo);
				
		return "packetCheck/packetCheck";
	}
	
	/**
	 * ドロップ率表示用のデータ取得.
	 * @return dropRates
	 */
	private List<DropLog> getDropRates() {
		
		// ドロップ率表示用のデータを取得
		List<DropLog> dropRates = packetCheckService.getDropRates();
		// 表示用Dtoを生成
		List<PacketCheckRateDisp> dropRatesInfo = new ArrayList<>();
		dropRates.forEach(dropRate -> {
			PacketCheckRateDisp packetCheckDisp = new PacketCheckRateDisp();
			packetCheckDisp.setLossRate(dropRate.getLossRate());
			dropRatesInfo.add(packetCheckDisp);
		});
		
		return dropRates;
	}
	
	/**
	 * detail informatin用のデータ取得.
	 * @return channelsInfo
	 */
	private List<PacketCheckDetailDisp> getDetailInfo() {
		
		// channel情報取得
		List<Channel> channels = packetCheckService.findAllChannel();
		
		// 表示用Dtoを生成
		List<PacketCheckDetailDisp> channelsInfo = new ArrayList<>();
		channels.forEach(channel -> {
			PacketCheckDetailDisp packetCheckDetailDisp = new PacketCheckDetailDisp();
			packetCheckDetailDisp.setId(channel.getId());
			packetCheckDetailDisp.setName(channel.getName());
			if ((new Integer(1).equals(channel.getStatus()))) {
				packetCheckDetailDisp.setStatus(MachineStatus.RUN);
			} else {
				packetCheckDetailDisp.setStatus(MachineStatus.STOP);
			}
			packetCheckDetailDisp.setMachineCount(channel.getMachineCount());
			packetCheckDetailDisp.setLocation(channel.getLocation());
			channelsInfo.add(packetCheckDetailDisp);
		});
		
		return channelsInfo;

	}
	
}
