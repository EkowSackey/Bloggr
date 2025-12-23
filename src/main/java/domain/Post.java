package domain;

import java.util.Date;

public record Post(String title,
                   String content,
                   Date dateCreated,
                   String authorId,
                   Object[] comments,
                   Object[] tags,
                   Object[] reviews)
{}
