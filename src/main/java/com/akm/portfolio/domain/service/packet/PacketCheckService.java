package com.akm.portfolio.domain.service.packet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akm.portfolio.controller.dto.rest.ChartData;
import com.akm.portfolio.controller.dto.rest.CurrentDropData;
import com.akm.portfolio.domain.model.packet.Channel;
import com.akm.portfolio.domain.model.packet.DropLog;
import com.akm.portfolio.domain.repository.packet.ChannelRepository;
import com.akm.portfolio.domain.repository.packet.DropLogRepository;


@Service
@Transactional
public class PacketCheckService {

	@Autowired
	ChannelRepository channelRepository;
	@Autowired
	DropLogRepository dropLogRepository;

	/**
	 * 全てのチャンネル情報を取得.
	 * @return channelIds
	 */
	public List<Channel> findAllChannel() {
		return channelRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	/**
	 * 画面表示用のドロップ率を取得.
	 * @return dropLogs
	 */
	public List<DropLog> getDropRates() {

		List<DropLog> dropLogs = new ArrayList<DropLog>();

		// ドロップ率を取得するChannelID群を設定
		List<Integer> channelIds = getChannelIds();
		
		// 全てのchannelIdに関して、最新のDropLogを取得する
		channelIds.forEach(channelId -> 
			dropLogs.add(dropLogRepository.getDropLogTimeStampDesc(channelId).get(0)));
		
		return dropLogs;
	}

	/**
	 * 最新のドロップ率を取得（RestAPI用）.
	 * リアルタイムで値を変更するため、ダミーデータを返す.
	 * @return currentDropData
	 */
	public CurrentDropData getCurrentDropData() {
		
		CurrentDropData currentDropData = new CurrentDropData();
		
		List<Integer> channelIds = getChannelIds();
		
		Random randomValue = new Random();
		channelIds.forEach(channelId -> {
			Integer value = randomValue.nextInt(31);
			currentDropData.getDropValues().put(channelId, value);
			});
		
		
		return currentDropData;
	}
	
	/**
	 * チャンネルIDを全て取得.
	 * @return channelIds
	 */
	private List<Integer> getChannelIds() {
		
		List<Integer> channelIds = new ArrayList<Integer>();
		List<Channel> channels = findAllChannel();
		channels.forEach(channel -> channelIds.add(channel.getId()));

		return channelIds;
	}
	
	public ChartData getChartData(Integer channelId) {
		
		ChartData chartData = new ChartData();
		List<DropLog> dropLogs = dropLogRepository.getDropLogTimeStampAsc(channelId);
		
		dropLogs.forEach(dropLog -> addChartDatas(chartData, dropLog));
		
		return chartData;
		
	}
	
	private void addChartDatas(ChartData chartData, DropLog dropLog) {
		
		// ラベルをセット
		String timeLabel = new SimpleDateFormat("HH:mm").format(dropLog.getTimestamp());
		chartData.getLabels().add(timeLabel);
		
		// 値をセット
		chartData.getValues().add(dropLog.getLossRate());
		
	}
	
}
