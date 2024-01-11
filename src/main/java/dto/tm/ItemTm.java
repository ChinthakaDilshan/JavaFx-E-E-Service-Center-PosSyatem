package dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemTm  {
    private String code;
    private String name;
    private String category;
    private JFXButton btn;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = (JFXButton) btn;
    }

    public ItemTm(String code, String name, String category, Button btn) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.btn = (JFXButton) btn;
    }



    @Override
    public String toString() {
        return "ItemTm{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", btn=" + btn +
                '}';
    }
}
