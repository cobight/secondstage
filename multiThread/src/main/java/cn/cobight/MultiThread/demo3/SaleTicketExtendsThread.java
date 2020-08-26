package cn.cobight.MultiThread.demo3;

/**
 * @ClassName SaleTicketExtendsThread\
 * @Description TODO
 * @Author cobight
 * @Date 2020/8/24 17:14
 * @VERTION 1.0
 **/
public class SaleTicketExtendsThread extends Thread{
    private static int totalTicket=20;//票数

    private String name;//人名

    public SaleTicketExtendsThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            if (totalTicket>0){
                System.out.println(Thread.currentThread().getName()+"正在卖票，剩余"+(totalTicket--)+"张");
            }else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        //需要将SaleTicketExtendsThread的票数静态才能资源共享，但是线程不安全，会导致多个线程操作一张票
        new SaleTicketExtendsThread("马云").start();
        new SaleTicketExtendsThread("马化腾").start();
        new SaleTicketExtendsThread("马飞").start();
    }
}
