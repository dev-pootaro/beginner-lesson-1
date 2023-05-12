const dateTileFormat = (dateTime) => {
  const year = dateTime.getFullYear();
  const month = dateTime.getMonth() + 1;
  const day = dateTime.getDate();
  const hour = dateTime.getHours();
  const minuts = dateTime.getMinutes();
  const seconds = dateTime.getSeconds();
  const zeroPadding = (num) => {
    return (num > 10) ? num : ('0' + num);
  }

  return year
        + '/'
        + zeroPadding(month)
        + '/'
        + zeroPadding(day)
        + '&nbsp;'
        + zeroPadding(hour)
        + ':'
        + zeroPadding(minuts)
        + ':'
        + zeroPadding(seconds);
};

function getFileList() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', 'http://localhost:9090/get-files', false);
  xhr.onload = () => {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        const res = JSON.parse(xhr.response);
        const fileListEl = document.querySelector('.file-list > table > tbody');
        res.forEach((r) => {
          const list = [];
          list.push('<tr>');
          list.push('<th scope="row">' + r.id + '</th>');
          list.push('<td>' + r.fileName + '</td>');
          list.push('<td>' + dateTileFormat(new Date(r.registDatetime)) + '</td>');
          list.push('<td>' + dateTileFormat(new Date(r.lastUpdateDatetime)) + '</td>');
          list.push('<td>' + `<button type="button" class="btn btn-primary download-button download-id-${r.id}" data-file-name="${r.fileName}">ダウンロード</button>` + '</td>');
          list.push('<td>' + `<button type="button" class="btn btn-secondary delete-button delete-id-${r.id}">削除</button>` + '</td>');
          list.push('</tr>');
          fileListEl.insertAdjacentHTML('beforeend', list.join(''));
        });
      } else {
        console.error(xhr.statusText);
      }
    }
  };
  xhr.onerror = (e) => {
    console.error(xhr.statusText);
  };
  xhr.setRequestHeader( 'Access-Control-Allow-Origin', '*' );
  xhr.send();
}

function onClickDownloadButton(e) { 
  const matchStrings = e.target.className.match(/download-id-([0-9])/);
  const fileName = e.target.dataset.fileName;
  const id = (matchStrings.length > 1) ? matchStrings[1] : null;
  if (id === null) {
    return;
  }
  const xhr = new XMLHttpRequest();
  xhr.open('GET', 'http://localhost:9090/get-file?id=' + id);
  xhr.setRequestHeader( 'Access-Control-Allow-Origin', '*' );
  xhr.responseType = 'blob';
  xhr.onload = () => {
    // finished download
    const blob = xhr.response;
    if (window.navigator.msSaveBlob) {
      // IE or Edge
      window.navigator.msSaveBlob(blob, fileName);
    } else {
      // Blob obj to URL obj
      const objectURL = window.URL.createObjectURL(blob);
      // create link element and click
      const link = document.createElement("a");
      document.body.appendChild(link);
      link.href = objectURL;
      link.download = fileName;
      link.click();
      document.body.removeChild(link);  
    }
  };
  xhr.onerror = () => {
    console.error(xhr.statusText);
  };
  xhr.send();
}

function onClickDeleteButton(e) { 
  const matchStrings = e.target.className.match(/delete-id-([0-9])/);
  const id = (matchStrings.length > 1) ? matchStrings[1] : null;
  if (id === null) {
    return;
  }
  const xhr = new XMLHttpRequest();
  xhr.open('DELETE', 'http://localhost:9090/delete?id=' + id);
  xhr.onload = (e) => {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        window.location.reload();
      } else {
        console.error(xhr.statusText);
      }
    }
  };
  xhr.onerror = () => {
    console.error(xhr.statusText);
  };
  xhr.setRequestHeader( 'Access-Control-Allow-Origin', '*' );
  xhr.send();
}

function onClickUpButton() {
  const inputFileEl = document.querySelector('#input-file');
  const file = (
    inputFileEl
    && inputFileEl.files
    && inputFileEl.files.length > 0
  ) ? inputFileEl.files[0] : null;

  if (!file) {
    return null;
  }

  const formData = new FormData();
  formData.append('file', file);

  const xhr = new XMLHttpRequest();
  xhr.open('POST', 'http://localhost:9090/upload');
  xhr.onload = (e) => {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        window.location.reload();
        console.log(xhr.responseText);
      } else {
        console.error(xhr.statusText);
      }
    }
  };
  xhr.onerror = () => {
    console.error(xhr.statusText);
  };
  xhr.setRequestHeader( 'Access-Control-Allow-Origin', '*' );
  xhr.send(formData);
}

function onInit() {
  getFileList();
  document.querySelector('.up-button').addEventListener('click', onClickUpButton);
  document.querySelectorAll('.download-button')?.forEach((el) => {
    el.addEventListener('click', onClickDownloadButton);
  });
  document.querySelectorAll('.delete-button')?.forEach((el) => {
    el.addEventListener('click', onClickDeleteButton);
  });
}

window.onload = onInit;