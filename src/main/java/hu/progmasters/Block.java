package hu.progmasters;

public class Block {
    private BlockHead blockHead;
    private BlockBody blockBody;
    private BlockFootNote blockFootNote;

    public Block() {
    }

    public BlockHead getBlockHead() {
        return blockHead;
    }

    public void setBlockHead(BlockHead blockHead) {
        this.blockHead = blockHead;
    }

    public BlockBody getBlockBody() {
        return blockBody;
    }

    public void setBlockBody(BlockBody blockBody) {
        this.blockBody = blockBody;
    }

    public BlockFootNote getBlockFootNote() {
        return blockFootNote;
    }

    public void setBlockFootNote(BlockFootNote blockFootNote) {
        this.blockFootNote = blockFootNote;
    }
}
