package com.promptoven.reviewReadService.infrastructure;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.global.common.utils.CursorPage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoReviewRepository extends MongoRepository<ReviewReadDocument, String> {
    List<ReviewReadDocument> findByProductUuid(String productUuid);

}
