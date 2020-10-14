package io.nimbus.reactive.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//TODO the reason the none standard annotations are used is that Spring uses them for none-relational dbs e.g. mongo.
//import javax.persistence.Column;
//import javax.persistence.Id;
//import javax.persistence.Table;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("posts")
public class Post {

    @Id
    @Column("id")
    private Integer id;

    @Column("title")
    private String title;

    @Column("content")
    private String content;
}
