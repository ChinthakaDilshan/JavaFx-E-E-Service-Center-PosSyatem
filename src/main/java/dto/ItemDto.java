package dto;

public class ItemDto {
    private String code;
    private String name;
    private String category;

    public ItemDto(String code, String name, String category) {
        this.code = code;
        this.name = name;
        this.category = category;
    }
    public ItemDto(){}
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


}
