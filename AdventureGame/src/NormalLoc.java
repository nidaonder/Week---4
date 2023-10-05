public abstract class NormalLoc extends Location{
    public NormalLoc(Player player, String name){
        super(player, name);
    }
    @Override
    public boolean onLocation() {
        // ölme ihtimali olmadığı için NormalLoc içinde true.
        return true;
    }
}
