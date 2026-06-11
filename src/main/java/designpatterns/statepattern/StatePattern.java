// State Design Pattern

package designpatterns.statepattern;

public class StatePattern {

    // State interface: defines actions that behave differently depending on the current state
    interface VendingMachineState {
        void insertCoin(VendingMachine machine);

        void selectItem(VendingMachine machine);

        void dispense(VendingMachine machine);
    }

    // Context: holds a reference to the current state and delegates all actions to it
    static class VendingMachine {
        private VendingMachineState state;

        VendingMachine() {
            // Machine starts in the idle state
            this.state = new IdleState();
        }

        void setState(VendingMachineState state) {
            this.state = state;
        }

        void insertCoin() {
            state.insertCoin(this);
        }

        void selectItem() {
            state.selectItem(this);
        }

        void dispense() {
            state.dispense(this);
        }
    }

    // Concrete state: waiting for a coin — only insertCoin transitions out of this state
    static class IdleState implements VendingMachineState {
        @Override
        public void insertCoin(VendingMachine machine) {
            System.out.println("Coin inserted. Please select an item.");
            machine.setState(new HasCoinState());
        }

        @Override
        public void selectItem(VendingMachine machine) {
            System.out.println("Insert a coin first.");
        }

        @Override
        public void dispense(VendingMachine machine) {
            System.out.println("Insert a coin and select an item first.");
        }
    }

    // Concrete state: coin received — only selectItem transitions out
    static class HasCoinState implements VendingMachineState {
        @Override
        public void insertCoin(VendingMachine machine) {
            System.out.println("Coin already inserted.");
        }

        @Override
        public void selectItem(VendingMachine machine) {
            System.out.println("Item selected. Dispensing...");
            machine.setState(new DispensingState());
            // Trigger dispensing immediately after selection
            machine.dispense();
        }

        @Override
        public void dispense(VendingMachine machine) {
            System.out.println("Select an item first.");
        }
    }

    // Concrete state: dispensing the item — automatically returns to idle after completion
    static class DispensingState implements VendingMachineState {
        @Override
        public void insertCoin(VendingMachine machine) {
            System.out.println("Please wait, dispensing in progress.");
        }

        @Override
        public void selectItem(VendingMachine machine) {
            System.out.println("Please wait, dispensing in progress.");
        }

        @Override
        public void dispense(VendingMachine machine) {
            System.out.println("Item dispensed. Returning to idle.");
            machine.setState(new IdleState());
        }
    }

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        // Happy path: insert coin -> select item -> auto-dispense -> back to idle
        machine.insertCoin();
        machine.selectItem();

        System.out.println();

        // Invalid actions in idle state
        machine.selectItem();
        machine.dispense();

        System.out.println();

        // Another full cycle
        machine.insertCoin();
        machine.insertCoin(); // duplicate coin
        machine.selectItem();
    }
}
