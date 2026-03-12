class TokenBucket {

    int tokens;
    int maxTokens;
    double refillRate;
    long lastRefillTime;

    public TokenBucket(int maxTokens, double refillRate) {
        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.lastRefillTime = System.currentTimeMillis();
    }

    // refill tokens based on elapsed time
    public void refill() {

        long now = System.currentTimeMillis();

        double tokensToAdd =
                ((now - lastRefillTime) / 1000.0) * refillRate;

        tokens = Math.min(maxTokens, tokens + (int)tokensToAdd);

        lastRefillTime = now;
    }

    public boolean allowRequest() {

        refill();

        if(tokens > 0){
            tokens--;
            return true;
        }

        return false;
    }
}
