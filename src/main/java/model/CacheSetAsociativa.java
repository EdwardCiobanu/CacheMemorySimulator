package model;

public class CacheSetAsociativa{
    private Integer cacheSize;
    private Integer memorySize;
    private Integer offsetBits;
    private Integer tag;
    private Integer index;
    private Integer offset;

    public CacheSetAsociativa() {
    }

    public CacheSetAsociativa(Integer cacheSize, Integer memorySize, Integer offsetBits) {
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

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
