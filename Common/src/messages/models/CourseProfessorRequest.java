package messages.models;

import utils.socket.IMessage;

public class CourseProfessorRequest implements IMessage {
    private final int professorId;

    public CourseProfessorRequest(int professorId) {
        this.professorId = professorId;
    }

    public int getProfessorId() {
        return professorId;
    }
}
