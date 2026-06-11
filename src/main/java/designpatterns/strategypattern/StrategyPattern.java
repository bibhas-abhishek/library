// Strategy Design Pattern

package designpatterns.strategypattern;

public class StrategyPattern {

    // Strategy interface: defines the contract all concrete strategies must implement
    interface PaymentStrategy {
        void pay(int amount);
    }

    // Concrete strategy: Credit card payment
    static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;

        CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public void pay(int amount) {
            System.out.println(
                    "Paid $"
                            + amount
                            + " via Credit Card ending in "
                            + cardNumber.substring(cardNumber.length() - 4));
        }
    }

    // Concrete strategy: PayPal payment
    static class PayPalPayment implements PaymentStrategy {
        private String email;

        PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(int amount) {
            System.out.println("Paid $" + amount + " via PayPal (" + email + ")");
        }
    }

    // Concrete strategy: Cryptocurrency payment
    static class CryptoPayment implements PaymentStrategy {
        private String walletAddress;

        CryptoPayment(String walletAddress) {
            this.walletAddress = walletAddress;
        }

        @Override
        public void pay(int amount) {
            System.out.println(
                    "Paid $" + amount + " via Crypto to " + walletAddress.substring(0, 8) + "...");
        }
    }

    // Context: uses a strategy but doesn't know which concrete implementation it holds
    static class ShoppingCart {
        private PaymentStrategy paymentStrategy;

        // Strategy can be swapped at runtime without changing the cart's code
        void setPaymentStrategy(PaymentStrategy strategy) {
            this.paymentStrategy = strategy;
        }

        void checkout(int amount) {
            if (paymentStrategy == null) {
                throw new IllegalStateException("No payment strategy set");
            }
            paymentStrategy.pay(amount);
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Pay with credit card
        cart.setPaymentStrategy(new CreditCardPayment("4111111111111234"));
        cart.checkout(150);

        // Swap to PayPal at runtime — no code change in ShoppingCart
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(75);

        // Swap to crypto
        cart.setPaymentStrategy(new CryptoPayment("0xABCDEF1234567890"));
        cart.checkout(300);
    }
}
