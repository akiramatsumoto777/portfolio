package com.akm.portfolio.domain.repository.packet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.akm.portfolio.domain.model.packet.DropLog;

public interface DropLogRepository extends JpaRepository<DropLog, Long> {

	@Query("SELECT dl FROM DropLog dl WHERE dl.channelId = :channelId ORDER BY dl.timestamp DESC")
	List<DropLog> getDropLogTimeStampDesc(@Param("channelId") Integer channelId);

	@Query("SELECT dl FROM DropLog dl WHERE dl.channelId = :channelId ORDER BY dl.timestamp ASC")
	List<DropLog> getDropLogTimeStampAsc(@Param("channelId") Integer channelId);

}
