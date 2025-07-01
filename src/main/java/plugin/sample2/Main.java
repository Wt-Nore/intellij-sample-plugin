package plugin.sample2;

import static java.nio.file.StandardOpenOption.APPEND;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;


public final class Main extends JavaPlugin implements Listener {

  private int count = 0;

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
  }

  String mailed = "info@raise-tecg.net";
  hoge@gmail.com
  alpha_info@yahoocojp


  /**
   * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
   *
   * @param e イベント
   */

  //しゃがんだ時に花火を打ち上げる
  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
    // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
    Player player = e.getPlayer();
    World world = player.getWorld();

    // BigInteger型の val を定義
    BigInteger val = new BigInteger(String.valueOf(count));
    count++;
    System.out.println(count + "回目です");
    //色のリスト
    List<Color> colorList = List.of(Color.YELLOW, Color.BLUE, Color.GREEN, Color.BLACK, Color.RED);

    // val が素数であるかの判定 isProbablePrimeメソッドを使用/
    if (val.isProbablePrime(20)) {
      //valの値を出力
      System.out.println(val + " は素数です");

      //もし、countが2の倍数になったとき、
      //if (count % 2 == 0) {
      //valの値を出力
      // System.out.println(count + "は偶数です");

      //拡張for文 何回繰り返すかを設定する　iはカウンタといい、このfor文の中でしか使われない変数。i=0を宣言。iの値が4より小さい時繰り返す。繰り返す旅にiが++される。
      for (Color color : colorList) {

        // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる。
        Firework firework = world.spawn(player.getLocation(), Firework.class);

        // 花火オブジェクトが持つメタ情報を取得。
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        // メタ情報に対して設定を追加したり、値の上書きを行う。
        // 今回は青色で星型の花火を打ち上げる。
        fireworkMeta.addEffect(
            FireworkEffect.builder()
                .withColor(color)
                //.withColor(Color.BLUE)
                .with(Type.BALL_LARGE)
                .withFlicker()
                .build());

        int Flying_distance = 0;
        Flying_distance = count;
        if(Flying_distance > 5){
          Flying_distance = count % 5 ;
        }

        player.sendMessage("花火を" + Flying_distance + "m発射します");
        System.out.println("花火の飛距離は" + Flying_distance + "mです");
        fireworkMeta.setPower(Flying_distance);

        // 追加した情報で再設定する。
        firework.setFireworkMeta(fireworkMeta);
      }
      //変数pathの中にtxtファイルを作成する、
      Path path = Path.of("firework.txt");
      Files.writeString(path,"たーまやー");
      player.sendMessage(Files.readString(path));
    }
  }


  /*
  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
    Player player = e.getPlayer();
    World world = player.getWorld();

    List<String> entitylist = Arrays.asList("Player", "Goat", "Cow");
    // 文字列の長さが「3」のものを抽出する。
    List filterList =
        entitylist.stream()
            .filter(entity -> entity.length() == 3)
            .collect(Collectors.toList());

    System.out.println(filterList);
    // 実行結果
    // [Cow]
  }
  */


/*
  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
    Player player = event.getPlayer();

    // しゃがみ始めたときのみ実行（立ち上がったときは無視）
    if (!event.isSneaking()) return;

    Location playerLocation = player.getLocation();
    Vector direction = playerLocation.getDirection().normalize();

    // プレイヤーの前方3ブロック先を計算
    Location targetLocation = playerLocation.clone().add(direction.multiply(3));

    // XZ座標を固定し、その地点の最高ブロックY座標を取得
    World world = player.getWorld();
    int highestY = world.getHighestBlockYAt(targetLocation);
    Location lavaLocation = new Location(world, targetLocation.getBlockX(), highestY + 1, targetLocation.getBlockZ());

    // その地点のブロックが空気であることを確認（上に置ける場合のみ）
    Block targetBlock = lavaLocation.getBlock();
    if (targetBlock.isEmpty()){
      targetBlock.setType(Material.DIAMOND_BLOCK);
    } else {
      player.sendMessage("そこにはマグマを置けません！");
    }
  }
  */



  @EventHandler
  public void onPlayerBedEnter(PlayerBedEnterEvent e) {
    Player player = e.getPlayer();
    //プレイヤーがベッドに入ったら、〜
    ItemStack[] itemStacks = player.getInventory().getContents();
    //インベントリ＝持ち物　から、　コンテンツ（=itemStacksとする）を持ってくる。コンテンツは、全ての種類を取得している

    //↓ベッドに入ろうとしたら、スタックできるアイテムの所持数を変更する
    for (ItemStack item : itemStacks) {
      if (!Objects.isNull((item))
          && item.getMaxStackSize() == 64
          && item.getAmount() < 64) {
        item.setAmount(64);
        //Objects.isNull(item) = インベントリが空欄なオブジェクトがあった場合、それはヌルになる !でそれを否定=ヌルじゃない
        //&& かつ
        //item.getMaxStackSize() =　アイテムの所持数を設定している。マイクラでは６４が最大
        //item.getAmount() < 64 =アイテムの現在の所持数が<数値未満だった場合
        //item.setAmount(64) = アイテムの所持数を64にする
    /*
    ↓for Eachに置換

    Arrays.stream(itemStacks)
       .filter(item -> !Objects.isNull(item) && item.getMaxStackSize() == 64 && item.getAmount() < 64)
       .forEach((item -> item.setAmount(itemStacks);
    */
      }
    }
    player.getInventory().setContents(itemStacks);
  }

}
//GitHub　学習コメント
//学習コメント２つ目
//PRコメント