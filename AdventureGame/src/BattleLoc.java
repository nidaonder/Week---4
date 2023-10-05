public class BattleLoc extends Location{
    public BattleLoc(Player player, String name){
        super(player, name);
    }
    @Override
    public boolean onLocation() {
        return false;
    }
}
