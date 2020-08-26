package cn.cobight.MultiThread.demo3;

/**
 * @ClassName SaleTicketImplementsRunnable\
 * @Description 实现runnable 借助于thread启动，可以是个对象，启动多次，实现资源共享
 * @Author cobight
 * @Date 2020/8/24 17:15
 * @VERTION 1.0
 **/
public class SaleTicketImplementsRunnable implements Runnable{

    private  int totalTicket = 20;

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
        //一个实现runnable的对象，三个线程执行，但是线程也不安全，会导致多个线程操作一张票
        SaleTicketImplementsRunnable saleTicketImplementsRunnable = new SaleTicketImplementsRunnable();
        new Thread(saleTicketImplementsRunnable,"马云").start();
        new Thread(saleTicketImplementsRunnable,"马化腾").start();
        new Thread(saleTicketImplementsRunnable,"马飞").start();
    }
}





















