package com.promptoven.reviewReadService.document;

import com.promptoven.reviewReadService.global.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "reviews")
public class ReviewReadDocument {

    @Id
    private String id;
    private Long reviewId;
    private String productUuid;
    private String contents;
    private int star;
    private String memberUuid;
    private String memberProfileImage;
    private String memberNickname;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public ReviewReadDocument(String id, Long reviewId, String productUuid, String contents, int star, String memberUuid, String memberProfileImage,
            String memberNickname, Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.reviewId = reviewId;
        this.productUuid = productUuid;
        this.contents = contents;
        this.star = star;
        this.memberUuid = memberUuid;
        this.memberProfileImage = memberProfileImage;
        this.memberNickname = memberNickname;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
