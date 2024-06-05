var boxIcons = document.getElementsByClassName('box');
for (let i = 0; i < boxIcons.length; i++) {
  // Mouse over
  boxIcons[i].onmouseover = () => {
    boxIcons[i].querySelector('.add-product').style.display = 'block';
  }
  // Mouse out
  boxIcons[i].onmouseout = () => {
    boxIcons[i].querySelector('.add-product').style.display = 'none';
  }
}

//  information Image in product detail
var avatar = document.getElementById('avatar');
var imagese = document.getElementsByClassName('img-infor');
for (let i = 0; i < imagese.length; i++) {
  imagese[i].onclick = () => {
    avatar.src = imagese[i].src;
  }
}