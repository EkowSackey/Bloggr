package domain;

public record Review(
        double stars,
        String userId,
        String postId
) {}
