package Enum.hw.task6;

public enum OrderStatus {
    NEW {
        @Override
        public OrderStatus next() {
            return PROCESSING;
        }
    },
    PROCESSING {
        @Override
        public OrderStatus next() {
            return SHIPPED;
        }
    },
    SHIPPED {
        @Override
        public OrderStatus next() {
            return DELIVERED;
        }
    },
    DELIVERED {
        @Override
        public OrderStatus next() {
            return null;
        }
    };

    public boolean canTransitionTo(OrderStatus target) {
        int tIndex = target.ordinal();
        if (this.ordinal() == tIndex - 1) {
            return true;
        }
        return false;
    }

    public abstract OrderStatus next();
}
