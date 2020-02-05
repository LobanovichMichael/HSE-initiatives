package ru.misha.telegram;

public class Task {
    String taskName;
    String taskText;
    String taskSolve;
    String taskAnswer;
    String taskHint;

    public Task(String taskName, String taskText, String taskSolve, String taskAnswer, String taskHint) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.taskSolve = taskSolve;
        this.taskAnswer = taskAnswer;
        this.taskHint = taskHint;
    }
}
