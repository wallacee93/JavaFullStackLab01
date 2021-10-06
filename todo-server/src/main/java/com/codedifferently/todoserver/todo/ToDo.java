package com.codedifferently.todoserver.todo;

public class ToDo {

    private Integer id;
    private String text;
    private Boolean isDone;

    private ToDo(Integer id, String text){
        this.id = id;
        this.text = text;
        this.isDone = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

    private static Integer indexNumber = 0;
    public static ToDo builder(String text){;
        Integer id = ++indexNumber;
        return new ToDo(id, text);
    }
}
