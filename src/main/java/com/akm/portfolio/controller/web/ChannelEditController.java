package com.akm.portfolio.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.akm.portfolio.controller.dto.web.ChannelEditDisp;
import com.akm.portfolio.controller.form.ChannelForm;
import com.akm.portfolio.domain.model.packet.Channel;
import com.akm.portfolio.domain.service.packet.ChannelEditService;

@Controller
public class ChannelEditController {
	
	@Autowired
	ChannelEditService channelEditService;
	
	@ModelAttribute
	ChannelForm setUpForm() {
		return new ChannelForm();
	}
	
	/**
	 * 画面表示.
	 * @param model
	 * @param channelId
	 * @return path
	 */
	@GetMapping("channelEdit/{channelId}")
	String pageDisplay(Model model, @PathVariable("channelId") Integer channelId) {
		
		// 表示用データを取得
		Channel channel = channelEditService.getChannelInfo(channelId);
		
		// 表示用Dtoにセット
		ChannelEditDisp channelEditDisp = new ChannelEditDisp();
		channelEditDisp.setId(channel.getId());
		channelEditDisp.setName(channel.getName());
		channelEditDisp.setLocation(channel.getLocation());
		channelEditDisp.setMachineCount(channel.getMachineCount());
		
		model.addAttribute("channelInfo", channelEditDisp);
		
		return "channel/edit";
	}
	
	/**
	 * 更新.
	 * @param channelForm
	 * @param bindingResult
	 * @param model
	 * @return path
	 */
	@PostMapping("channelEdit")
	String update(@Validated ChannelForm channelForm, 
			BindingResult bindingResult, Model model) {

		// validation結果チェック
		if (bindingResult.hasErrors()) {
			model.addAttribute("channelInfo", channelForm);
			return "channel/edit";
		}
		
		//更新処理
		Channel channelInfo = channelEditService.getChannelInfo(channelForm.getId());
		channelInfo.setName(channelForm.getName());
		channelInfo.setLocation(channelForm.getLocation());
		channelInfo.setMachineCount(Integer.valueOf(channelForm.getMachineCount()));
		// 本来なら楽観的排他チェックを入れるが割愛
		channelEditService.update(channelInfo);
		
		return "redirect:/packetCheck";

	}

}
