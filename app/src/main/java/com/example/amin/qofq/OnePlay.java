package com.example.amin.qofq;

public class OnePlay
{
    int[] Qs = new int[12];
    int Q = 0;
    public OnePlay()
    {}
    public void Add(int q)
    {
        Qs[Q++] = q;
    }
}
