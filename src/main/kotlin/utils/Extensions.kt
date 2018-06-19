package utils

fun TreeNode?.isLeaf(): Boolean = this != null && this.left == null && this.right == null

fun TreeNode?.isTreeEqualTo(anotherRoot: TreeNode?): Boolean =
        when {
            this == null && anotherRoot == null -> true
            this != null && anotherRoot != null -> when {
                this.isLeaf() && anotherRoot.isLeaf() -> this.value == anotherRoot.value
                !this.isLeaf() && !anotherRoot.isLeaf() -> {
                    this.left?.isTreeEqualTo(anotherRoot.left) ?: true
                            && this.right?.isTreeEqualTo(anotherRoot.right) ?: true
                }
                else -> false
            }
            else -> false
        }
