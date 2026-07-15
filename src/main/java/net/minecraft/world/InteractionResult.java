package net.minecraft.world;

public interface InteractionResult {
    InteractionResult PASS = new InteractionResult() {
        @Override
        public boolean consumesAction() {
            return false;
        }
    };

    InteractionResult SUCCESS = new Success() {
        @Override
        public boolean consumesAction() {
            return true;
        }
    };

    InteractionResult CONSUME = new Success() {
        @Override
        public boolean consumesAction() {
            return true;
        }
    };

    InteractionResult FAIL = new InteractionResult() {
        @Override
        public boolean consumesAction() {
            return false;
        }
    };

    boolean consumesAction();

    interface Success extends InteractionResult {
    }
}
