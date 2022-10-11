package com.socialmedia.comments.util;

public class EndPoints {
   public static final String END_POINT_PREFIX = "/v1/demo/";
   public static final String USERS = END_POINT_PREFIX +"users";
   public static final String POSTS = END_POINT_PREFIX + "posts";
   public  static final String POST_ID = "/{postId}/";
   public  static final String COMMENT_ID = "/{commentId}";
   public static final String POSTS_COMMENTS = POST_ID+"comments";
   public static final String COMMENTS = END_POINT_PREFIX + "comments";
   public static final String REPLIES = COMMENT_ID +"/" + "replies";
   public static final String POSTS_COMMENTS_COMMENT = POSTS_COMMENTS+COMMENT_ID;
   public static final String LIKES = "likes";
   public static final String DISLIKES = "dislikes";
   public static final String LIKE_POST = POST_ID + LIKES;
   public static final String DISLIKE_POST =  POST_ID + DISLIKES;
   public static  final String LIKE_COMMENT = COMMENT_ID +"/"+ LIKES;
   public static final String DISLIKE_COMMENT =  COMMENT_ID + "/" + DISLIKES;

}
