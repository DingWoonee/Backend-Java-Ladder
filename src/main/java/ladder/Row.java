package ladder;

public class Row {

    Node[] row;

    public Row(int numberOfPerson) {
        this.row = new Node[numberOfPerson];
        for (int i = 0; i < numberOfPerson; i++) {
            row[i] = Node.of(Direction.NONE);
        }
    }

    public boolean isLeft(Position position) {
        return row[position.getValue() - 1].getDirection() == Direction.LEFT;
    }

    public boolean isRight(Position position) {
        return row[position.getValue() - 1].getDirection() == Direction.RIGHT;
    }

    public Position nextPosition(Position position) {
        if (isLeft(position)) {
            return position.prev();
        }
        if (isRight(position)) {
            return position.next();
        }
        return position;
    }

    public void drawLine(Position position, Direction direction) {
        //밖으로 그리기 금지
        if (position.equals(Position.of(this.row.length)) && direction.equals(Direction.RIGHT)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DIRECTION.getMessage());
        }
        if (position.equals(Position.of(1)) && direction.equals(Direction .LEFT)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DIRECTION.getMessage());
        }
        //겹치게 그리기 금지
        if (row[position.getValue() - 1].getDirection() != Direction.NONE
                || row[position.getValue() + direction.getValue() - 1].getDirection() != Direction.NONE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_POSITION.getMessage());
        }
        //그리기
        row[position.getValue() - 1] = Node.of(direction);
        row[nextPosition(position).getValue() - 1] = Node.of(direction.getOppositeDirection());
    }
}
