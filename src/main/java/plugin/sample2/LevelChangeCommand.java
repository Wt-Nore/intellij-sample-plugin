package plugin.sample2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class LevelChangeCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    //"/gamemode creative" ←はゲーム内でのコマンド入力
    //creative は引数
    if (sender instanceof Player player) {
      //senderがPlayerであれば
      if(args.length > 0) {
        try {
          int level = Integer.parseInt(args[0]);//文字列を整数に変換
          player.setLevel(level);
          player.sendMessage("レベルが" + level + "になった！");
        } catch (NumberFormatException e) {
          player.sendMessage("有効な数字を入力してください");
        }
      } else {
        player.sendMessage("exp:/levelchange 10");
      }
      //player.setLevel(30);//元
      return  true;//コマンド成功
    }
    return false;//コマンド送信者がプレイヤー出ない場合
  }
}