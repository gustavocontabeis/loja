function msg(data) {
	if (data.msg != undefined) {
		if (data.ok) {
			toastr.info(data.msg);
		} else {
			toastr.error(data.msg);
		}
	}
}
function msgOK(msg) {
	toastr.info(msg);
}
function msgErro(msg) {
	toastr.error(msg);
}
function retorno(data){
	msg(data);
	return data.obj;
}