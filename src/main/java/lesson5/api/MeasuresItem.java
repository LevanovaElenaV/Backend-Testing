package lesson5.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MeasuresItem {
    private Long id;
    private String title;
    private String image;
    private String imageType;

}
