package com.test.flow.domain;

import com.test.flow.domain.enums.FixedEnum;
import com.test.flow.dto.CustomResponse;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CustomExtension extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String names;

    @Column(nullable = false)
    private boolean flag = true;

    @Builder
    public CustomExtension(String names, boolean flag) {
        this.names = names;
        this.flag = flag;
    }

    public void update(CustomResponse customResponse) {
        this.names = customResponse.getNames();
        if (this.flag) {
            this.flag = false;
        } else {
            this.flag = true;
        }
    }
}
