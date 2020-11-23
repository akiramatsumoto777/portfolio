package com.akm.portfolio.domain.service.packet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akm.portfolio.domain.model.packet.Channel;
import com.akm.portfolio.domain.repository.packet.ChannelRepository;

@Service
@Transactional
public class ChannelEditService {
	
	@Autowired
	ChannelRepository channelRepository;
	
	/**
	 * チャンネル情報を取得.
	 * @param channelId
	 * @return
	 */
	public Channel getChannelInfo(Integer channelId) {
		
		Channel channelInfo = channelRepository.getOne(channelId);
		
		return channelInfo;
	}

	/**
	 * チャンネル情報を更新.
	 * @param channel
	 * @return channel
	 */
	public Channel update(Channel channel) {
		return channelRepository.save(channel);
	}


}
