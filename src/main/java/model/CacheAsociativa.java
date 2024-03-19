package model;

public class CacheAsociativa{
    private Integer cacheSize;
    private Integer memorySize;
    private Integer offsetBits;
    private Integer block;
    private Integer offset;

    public CacheAsociativa() {
    }

    public CacheAsociativa(Integer cacheSize, Integer memorySize, Integer offsetBits) {
        this.cacheSize = cacheSize;
        this.memorySize = memorySize;
        this.offsetBits = offsetBits;
    }

    public Integer getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(Integer cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Integer memorySize) {
        this.memorySize = memorySize;
    }

    public Integer getOffsetBits() {
        return offsetBits;
    }

    public void setOffsetBits(Integer offsetBits) {
        this.offsetBits = offsetBits;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
