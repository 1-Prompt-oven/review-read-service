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
public class ReviewReadDocument extends BaseEntity {

    @Id
    private String id;
    private Long reviewId;
    private String productUuid;
    private String contents;
    private int star;
    private String authorUuid;
    private String authorProfileImage;
    private String authorNickname;
    private Boolean isDeleted;


    @Builder
    public ReviewReadDocument(String id, Long reviewId, String productUuid, String contents, int star,
            String authorUuid, String authorProfileImage,
            String authorNickname, Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.reviewId = reviewId;
        this.productUuid = productUuid;
        this.contents = contents;
        this.star = star;
        this.authorUuid = authorUuid;
        this.authorProfileImage = authorProfileImage;
        this.authorNickname = authorNickname;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
