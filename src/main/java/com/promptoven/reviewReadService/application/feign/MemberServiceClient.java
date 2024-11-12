package com.promptoven.reviewReadService.application.feign;

import com.promptoven.reviewReadService.dto.in.MemberProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service", url = "http://localhost:8081") // Eureka 에 등록된 서비스 이름
public interface MemberServiceClient {

    @GetMapping("/v1/member/review/profile")
    MemberProfileDto getMemberProfile();
}
