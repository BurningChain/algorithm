package com.leetcoe.solution.day05;

class IP implements Comparable<IP>{
    public String ip;
    public int nums;
    public IP(){}
    public IP(String ip,int nums){
        this.ip = ip;
        this.nums = nums;
    }
    @Override
    public int compareTo(IP o) {
        if (this.ip.equals(o.ip)) {
            o.nums=this.nums+o.nums;
        }else {
            if (this.nums > o.nums) {
                return -1;
            }else{
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IP) {
            IP that = (IP) obj;
            return this.nums == that.nums && this.ip.equals(that.ip);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.ip.hashCode() * 31 + this.nums * 31;
    }
}
