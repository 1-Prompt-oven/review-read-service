package com.promptoven.reviewReadService.document;

import com.promptoven.reviewReadService.global.common.entity.BaseEntity;
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
    private String productUuid;
    private String contents;
    private String memberUuid;
    private String memberProfileImage;
    private String memberNickname;
    private Boolean isDeleted;

    @Builder
    public ReviewReadDocument(String productUuid, String contents, String memberUuid, String memberProfileImage,
            String memberNickname, Boolean isDeleted) {
        this.productUuid = productUuid;
        this.contents = contents;
        this.memberUuid = memberUuid;
        this.memberProfileImage = memberProfileImage;
        this.memberNickname = memberNickname;
        this.isDeleted = isDeleted;
    }
}
