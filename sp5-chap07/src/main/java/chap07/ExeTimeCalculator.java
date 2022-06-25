package chap07;

public class ExeTimeCalculator implements Calculator
{
    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate)
    {
        this.delegate = delegate;
    }

    @Override
    public long factorial(long num)
    {
        long start = System.nanoTime();
        // 전달 받은 Calculator 객체로 펙토리얼 계산 시작
        long result = delegate.factorial(num);
        long end = System.nanoTime();
        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n", delegate.getClass().getSimpleName(), num, (end-start));
        return result;
    }
}
