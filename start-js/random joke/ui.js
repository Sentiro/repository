const customName = document.getElementById('customname');
const randomize = document.querySelector('.randomize');
const story = document.querySelector('.story');


function randomValueFromArray(array){
  return array[Math.floor(Math.random()*array.length)];
}
var storyText="It was 94 fahrenheit outside, so :insertx: went for a walk. When they got to :inserty:, they stared in horror for a few moments, then :insertz:. Bob saw the whole thing, but was not surprised — :insertx: weighs 300 pounds, and it was a hot day.";
var insertX=["Willy the Goblin","Big Daddy", "Father Christmas"];
var insertY=["the soup kitchen","Disneyland","the White House"];
var insertZ=["spontaneously combusted","melted into a puddle on the sidewalk","turned into a slug and crawled away"];

randomize.addEventListener('click', result);

function result() {
    var newStory;
    var xItem;
    var yItem;
    var zItem;
    var name;
if(document.getElementById('cn').checked){
      if(customName.value !== '') {
    var name = customName.value;
    newStory=newStory.replace("李雷",name);
  }
}else{
    newStory=storyText;

    xItem=randomValueFromArray(insertX);
    yItem=randomValueFromArray(insertY);
    zItem=randomValueFromArray(insertZ);

    var tempStory=newStory.replace(":insertx:",xItem);
    tempStory=tempStory.replace(":inserty:",yItem);
    newStory=tempStory.replace(":insertz:",zItem);

    if(customName.value!==""){
        name=customName.value;
        newStory=newStory.replace("Bob",name);
    }
}


  if(document.getElementById("uk").checked) {
    var weight = Math.round(300*0.0714286)+'stone';
    var temperature =  Math.round((94-32)*5/9)+'centigrade';
    newStory=newStory.replace('94 fahrenheit',temperature);
    newStory=newStory.replace('300 pounds',weight);

  }

  story.style.visibility = 'visible';
}
document.getElementById('cn').onclick = () => {
    document.title = '笑话生成器';
    document.getElementById('lbl-customname').textContent = '请输入自定义的名字：';
    document.getElementById('lbl-cn').textContent = '中国';
    document.getElementById('lbl-us').textContent = '美国';
    document.getElementById('lbl-uk').textContent = '英国';
    document.getElementById('customname').placeholder = '李雷';
    document.querySelector('.randomize').textContent = '随机生成笑话';
  };
  
  document.getElementById('us').onclick =
  document.getElementById('uk').onclick = () => {
    document.title = 'Silly story generator';
    document.getElementById('lbl-customname').textContent = 'Enter custom name:';
    document.getElementById('lbl-cn').textContent = 'CN';
    document.getElementById('lbl-us').textContent = 'US';
    document.getElementById('lbl-uk').textContent = 'UK';
    document.getElementById('customname').placeholder = 'Bob';
    document.querySelector('.randomize').textContent = 'Generate random story';
  };

  story.textContent=newStory;;