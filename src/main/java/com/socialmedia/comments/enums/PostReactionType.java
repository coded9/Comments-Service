package com.socialmedia.comments.enums;

import lombok.Getter;

@Getter
public enum PostReactionType {
   LIKE("like"),
   DISLIKE("dislike");

   private final String value;
   PostReactionType(String value){
      this.value = value;
   }


}
