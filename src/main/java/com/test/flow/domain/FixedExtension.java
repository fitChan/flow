package com.test.flow.domain;

import com.test.flow.domain.enums.FixedEnum;
import com.test.flow.dto.FixedResponse;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class FixedExtension extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private FixedEnum names;

    @Column(nullable = false)
    private boolean flag = true;

    @Builder
    public FixedExtension(FixedEnum names, boolean flag) {
        this.names = names;
        this.flag = flag;
    }
    public void update(FixedResponse names){
        this.names = names.getNames();
        if(this.flag){
            this.flag = false;
        }else{
            this.flag = true;
        }
    }


}
