package domain;

public record Comment(
        String content,
        String authorId,
        String parentId,
        Comment[] subComments
) {}
