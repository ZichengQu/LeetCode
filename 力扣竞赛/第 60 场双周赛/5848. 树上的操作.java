// https://leetcode-cn.com/problems/operations-on-tree/

class LockingTree {
	private int[] parent; // 输入，每个结点的父结点
    private int[] lock; // 每个结点的锁的状态
	private ArrayList<Integer>[] list; // 每个结点的子结点列表

	public LockingTree(int[] parent) {
		this.parent = parent;
		lock = new int[parent.length];
		list = new ArrayList[parent.length];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 1; i < parent.length; i++) {
			list[parent[i]].add(i); // 将每个结点添加到它父结点的 list 中。每个结点会记录一个子结点列表
		}
	}

	public boolean lock(int num, int user) {
		if (lock[num] == 0) {
			lock[num] = user;
			return true;
		}
		return false;
	}

	public boolean unlock(int num, int user) {
		if (lock[num] == user) {
			lock[num] = 0;
			return true;
		}
		return false;
	}

	public boolean upgrade(int num, int user) {
        if(lock[num] != 0){ // 如果自身被锁住
            return false;
        }
        if (ancestors(num) && children(num)) {
			lock[num] = user;
			return true;
		}
		return false;
	}

    private boolean ancestors(int num) { // 如果祖先有任何结点被锁住
        while(true){
            num = parent[num];
            if(num == -1){
                break;
            }
            if(lock[num] != 0){
                return false;
            }
        }
        return true;
	}

	private boolean children(int num) { // 如果子孙至少有一个被锁住
		boolean flag = false;
        if(lock[num] != 0){
            flag = true;
        }
        for(int child: list[num]){
            flag |= children(child);
        }

        if(flag){
            lock[num] = 0; // 锁住的，需要解锁。没锁的，本来就已经是解锁状态了。因此最终状态一致，不需要判断是否锁了
        }

        return flag;
	}
}