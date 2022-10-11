create table if not exists app_user(
id bigint primary key auto_increment,
full_name varchar(255) not null,
user_name varchar(255) not null,
profile_pic_url varchar(255),
created_at datetime not null,
updated_at datetime not null,
unique(user_name)
);

create table if  not exists post(
id bigint primary key auto_increment,
content text not null,
user_id bigint not null,
created_at datetime not null,
updated_at datetime not null,
foreign key(user_id) references app_user(id)
);

create table if not exists comment(
id bigint primary key auto_increment,
content text not null,
user_id bigint not null,
post_id bigint not null,
is_parent tinyint not null default 1,
created_at datetime not null,
updated_at datetime not null,
foreign key(user_id) references app_user(id),
foreign key(post_id) references post(id)
);
create table if not exists reply(
id bigint primary key auto_increment,
parent_comment_id bigint not null,
child_comment_id bigint not null,
foreign key(parent_comment_id) references comment(id),
foreign key(child_comment_id) references comment(id)
);

create table if not exists post_reaction(
  id bigint primary key auto_increment,
  post_id bigint not null,
  user_id bigint not null,
  reaction_type enum('LIKE','DISLIKE'),
  created_at datetime not null,
  updated_at datetime not null,
  unique(post_id,user_id),
  foreign key(user_id) references app_user(id),
  foreign key(post_id) references post(id)
);

create table if not exists comment_reaction(
  id bigint primary key auto_increment,
  comment_id bigint not null,
  user_id bigint not null,
  reaction_type enum('LIKE','DISLIKE'),
  created_at datetime not null,
  updated_at datetime not null,
  unique(comment_id,user_id),
  foreign key(user_id) references app_user(id),
  foreign key(comment_id) references comment(id)
);

create table if not exists comment_interaction_summary(
 id bigint primary key auto_increment,
 comment_id bigint not null,
 likes_count int not null,
 dislikes_count int not null,
 replies_count int not null,
 updated_at datetime not null,
 foreign key(comment_id) references comment(id)
);
create table if not exists post_interaction_summary(
 id bigint primary key auto_increment,
  post_id bigint not null,
  likes_count int not null,
  dislikes_count int not null,
  comments_count int not null,
  updated_at datetime not null,
  foreign key(post_id) references post(id)
);
