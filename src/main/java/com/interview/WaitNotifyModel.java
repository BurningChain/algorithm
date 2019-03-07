package com.interview;

public class WaitNotifyModel {
/*
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Product product = new Product(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(product).start();
        new Thread(consumer).start();
    }*/

}

/*
class Clerk {

    private int product = 0;

    public synchronized void addProduct() throws Exception {

        if (product >= 20) {
            this.wait();
        } else {
            System.out.println("make product, now the product num is: " + (++product));
            this.notifyAll();
        }

    }

    public synchronized void saleProduct() throws Exception {
        if (product > 0) {
            System.out.println("sale product one, now the product is: " + (--product));
            this.notifyAll();
        } else {
            this.wait();
        }
    }
}

class Product implements Runnable {

    private Clerk clerk;

    public Product(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);

                clerk.addProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                clerk.saleProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
*/


