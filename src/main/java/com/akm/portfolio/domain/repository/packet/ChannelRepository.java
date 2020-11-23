package com.akm.portfolio.domain.repository.packet;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akm.portfolio.domain.model.packet.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {
}