package com.ferro.mateus.rabbitmq_w_spring_sender.controller.dtos;

import java.util.List;

public record APIResponse<T>(List<T> results, PageResponse paging) {
}
