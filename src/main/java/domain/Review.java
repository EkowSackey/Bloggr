package domain;

import org.bson.types.ObjectId;

public record Review(
        double stars,
        ObjectId userId,
        ObjectId postId
) {}
